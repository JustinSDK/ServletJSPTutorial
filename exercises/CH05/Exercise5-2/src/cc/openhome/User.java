package cc.openhome;

public class User  {
    public final String name;
    private final String remoteAddr;
    private final String userAgent;
    
    public String data;
    
    public User(String name, String remoteAddr, String userAgent) {
        this.name = name;
        this.remoteAddr = remoteAddr;
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", name, remoteAddr, userAgent, data);
    }
}
