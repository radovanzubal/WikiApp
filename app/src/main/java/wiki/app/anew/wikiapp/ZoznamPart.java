package wiki.app.anew.wikiapp;

public class ZoznamPart {
    private String title,snippet;
    private boolean saved;
    private int pageID,sizeBytes;
    public ZoznamPart(String title,String snippet,boolean saved,int pageID,int sizeBytes) {
        this.title = title;
        this.snippet = snippet;
        this.saved = saved;
        this.pageID=pageID;
        this.sizeBytes=sizeBytes;
    }
    public String getTitle(){return title;}
    public int getSizeBytes(){return sizeBytes;}

    public String getSnippet(){return snippet;}
    public boolean getSaved(){return saved;}
    public int getPageID(){return pageID;}
}
