package cc.openhome;
    
public class MessageService {
    // 放些假資料，假裝這些資料是來自資料庫
    private Message[] fakeMessages = {
         new Message("caterpillar", "caterpillar's message!"),
         new Message("momor", "momor's message!"),
         new Message("hamimi", "hamimi's message!")
    };
    
    public Message[] getMessages() {
        return fakeMessages;
    }
} 