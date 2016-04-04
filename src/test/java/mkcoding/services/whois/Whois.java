package mkcoding.services.whois;

import java.io.*;
import java.net.*;

/**
 * Created by mx on 16/4/4.
 */
public class Whois {

    public final static int DEFAULT_PORT = 43;

    public final static String DEFAULT_HOST = "whois.internic.net";

    private int port = DEFAULT_PORT;
    private InetAddress host;

    public Whois(InetAddress host, int port) {
        this.port = port;
        this.host = host;
    }

    public Whois(InetAddress host) {
        this.host = host;
    }

    public Whois(String hostname, int port) throws UnknownHostException {
        this(InetAddress.getByName(hostname), port);
    }

    public Whois(String hostname) throws UnknownHostException {
        this(InetAddress.getByName(hostname), DEFAULT_PORT);
    }

    public Whois() throws UnknownHostException {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    //搜索条目
    public enum SearchFor {
        ANY("any"), NETWORK("Network"), PERSON("Person"), HOST("Host"), DOMAIN("Domain"),
        ORGANIZATION("Organization"), GROUP("Group"), GATEWAY("Gateway"), ASN("ASN");

        private String label;

        SearchFor(String label) {
            this.label = label;
        }
    }

    //搜索类别
    public enum SearchIn {
        ALL(""), NAME("Name"), MAILBOX("Mailbox"), HANDLE("!");

        private String label;

        SearchIn(String label) {
            this.label = label;
        }
    }

    public String lookUpNames(String target, SearchFor category, SearchIn group, Boolean exactMatch) throws IOException {
        String suffix = "";
        if (!exactMatch) suffix = ".";

        String prefix = category.label + " " + group.label;
        String query = prefix + target + suffix;

        Socket socket = new Socket();
        try {
            SocketAddress address = new InetSocketAddress(host, port);
            socket.connect(address);
            Writer out = new OutputStreamWriter(socket.getOutputStream(), "ASCII");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "ASCII"));

            out.write(query + "\r\n");
            out.flush();

            StringBuilder response = new StringBuilder();
            String theline = null;

            while ((theline = in.readLine()) != null) {
                response.append(theline);
                response.append("\r\n");
            }

            return response.toString();
        } finally {
            socket.close();
        }
    }

    public InetAddress getHost() {
        return this.host;
    }

    public void setHost(String host) throws UnknownHostException {
        this.host = InetAddress.getByName(host);
    }
}
