import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

/**
 * Denne klasse bruges til at interagere med testserveren til rafleb�geropgaverne.
 *
 * Hvis det er første gang du tilgår testserveren, bliver du bedt om at indtaste en testkode,
 * der kan findes på Grade Center inde på Blackboard.
 *
 * Bem�rk: Du kan kun tilgå testserveren fra universitetes netværk.
 *
 * Guide til at tilgå universitetets netværk hjemmefra:
 * https://medarbejdere.au.dk/administration/it/vpn-remoteaudk/
 *
 * Hvis du oplever fejl ved brug af denne klasse, bedes du skrive til nis@cs.au.dk
 * og beskrive dit problem så præcist som muligt.
 *
 * @author  Nikolaj I. Schwartzbach
 * @version 02-04-2019
 */
public class TestServer {

    // Gem konstrukt�ren
    private TestServer(){}

    /**
     * Tests exercise in classpath (the folder from which the program is invoked).
     * @param exercise   Short name of exercise (DC1, DC2, DC3-1, DC3-4).
     */
    public static void test(String exercise) throws IOException {

        String ex = exercise.toLowerCase();

        // Switch on 'h' to get list of files.
        String[] files = null;
        switch(ex){
            case "dc1":
            case "dc2":
            case "dc3-1":
            case "dc3-4":
            files = new String[]{"Die", "DieCup"};
            break;
            default:
            System.err.println("Ugyldig aflevering: "+exercise+".");
            return;
        }

        Map<String,String> arguments = new HashMap<>();

        // Check upload code
        String auID="", code = "";

        // Check if auID/code are saved
        Path dataPath = Paths.get("upload-data.dat");
        if(Files.exists(dataPath)){
            // Read first line
            String line = Files.readAllLines(dataPath).get(0);

            // Split into auID and code
            String[] data = line.split(" ");

            auID = data[0];
            code = data[1];
        }

        // Check if information is missing
        if(auID.isEmpty() || code.isEmpty()){
            // Get user input
            Scanner s = new Scanner(System.in);

            System.out.print("Det ser ud til, du endnu ikke har uploadet til testserveren.\n" + 
                "For at forts�tte, skal vi bruge dit auID og uploade kode (findes p� Blackboard).\n" +
                "Indtast auID: ");

            auID = s.nextLine();
            System.out.print("Indtast upload kode: ");
            code = s.nextLine();

            // Save information for next time
            PrintWriter pw = new PrintWriter("upload-data.dat");
            pw.println(auID + " " + code);
            pw.close();
        }

        // Set meta information
        arguments.put("h",ex.replace("dc3-1","dc3a").replace("dc3-4","dc3b"));
        arguments.put("auID",auID);
        arguments.put("code",code);

        // Check GDPR
        Map<String, String> args = new HashMap<>();
        args.put("auID",auID);
        String res = sendPost("http://dintprog-server.cs.au.dk/gdpr.php", args);
        if(!res.startsWith("GDPR success")) {
            System.out.print("\nAf hensyn til overholdelse af GDPR, har vi behov for dit samtykke til behandling af dine personoplysninger.\n"+
                "Nikolaj I. Schwartzbach er dataansvarlig og kan kontaktes pr. email p� nis@cs.au.dk.\n" +
                "Vi behandler dine personoplysninger til flg. form�l:"+
                "\n - Administrering af afleveringer til kurset Introduktion til Programmering.\n" +
                "\nVi behandler flg. personoplysninger om dig:\n" +
                " - Dit fulde navn\n"+
                " - Dit auID og studienr.\n"+
                " - Metadata om alle uploads (herunder tidspunkt/useragent mm.)\n"+
                "\nDine personoplysninger videregives ikke tredjeparter og behandles kun i EU/E�S.\n"+
                "\nDine personoplysninger gemmes p� ubestemt tid, men du kan til enhver tid f� din information slettet/rettet.\n"+
                "\nSamtykker du til dette (skriv 'Ja')?\n:> ");
            Scanner s = new Scanner(System.in);
            String answer = s.nextLine().toLowerCase();

            // Check answer
            if(answer.equals("ja")){
                // Log consent
                try{
                    args = new HashMap<>();
                    args.put("auID",auID);
                    args.put("code",code);
                    res = sendPost("http://dintprog-server.cs.au.dk/gdpr.php", args);
                    if(!res.startsWith("GDPR success")){
                        if(res.startsWith("Invalid")) {
                            try{
                                System.out.println("\nForkert auID eller upload kode.");
                                Files.delete(dataPath);
                            }
                            catch(IOException e) {
                                e.printStackTrace();
                            }
                        } else System.out.println("\n"+res);
                        return;
                    }
                } catch(IOException e){ e.printStackTrace(); return; }
            } else return;
            System.out.println();
        }

        // Check all files and accumulate contents
        for(String file : files){
            Path p = Paths.get(file+".java");

            // Check if file exists
            if(Files.exists(p)){
                String str = new String(Files.readAllBytes(p), Charset.defaultCharset());
                arguments.put(file.toLowerCase(), str);
            } else {
                System.err.println("No file with this name '" + file + ".java' in folder " + System.getProperty("user.dir")  +".");
                return;
            }
        }

        // Send request
        try {
            // Send request and handle response
            String response = sendPost("http://dintprog-server.cs.au.dk/upload.php",arguments);
            System.out.println(response);        
            if(response.startsWith("Invalid")) {
                try{
                    Files.delete(dataPath);
                    test(ex);
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        } catch(java.net.UnknownHostException e){
            System.out.println("Du er sandsynligvis ikke p� universitets netv�rk. F�lg flg. guide for at aktivere VPN: \n - https://medarbejdere.au.dk/administration/it/vpn-remoteaudk/");
        }
    }

    /**
     * This method sends an HTTP POST request to a specified location with some arguments.
     * @param location The URL to POST to.
     * @param arguments The HTTP header arguments.
     * @return The response from the URL (if any).
     * @throws IOException For various reasons.
     */
    private static String sendPost(String location, Map<String,String> arguments) throws IOException {

        URL url = new URL(location);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        con.setRequestProperty("User-Agent", "Java client");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                + URLEncoder.encode(entry.getValue(), "UTF-8"));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
        StringBuilder content;

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(http.getInputStream()))) {

            String line;
            content = new StringBuilder();

            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        }

        http.disconnect();
        return content.toString();
    }
}
