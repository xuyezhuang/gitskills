package cn.dooer.ydz.retrofitrxtest.eventbus;

public class MessageEvent {
    private String message;
    public MessageEvent(String message){
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
}
