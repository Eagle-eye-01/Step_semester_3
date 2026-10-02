package week_7.class_problems;

public abstract class MediaFile {
    private static int counter = 1000;
    private final String fileId;

    public MediaFile() {
        this.fileId = "MF-" + (++counter);
    }

    public abstract String getFormatInfo();

    public String getFileId() {
        return fileId;
    }
}
