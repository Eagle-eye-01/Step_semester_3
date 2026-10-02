package week_7.class_problems;

public class Podcast implements Playable {
    private final String showName;
    private final int episodeNumber;

    public Podcast(String showName, int episodeNumber) {
        if (episodeNumber <= 0) {
            throw new IllegalArgumentException("episodeNumber must be positive");
        }
        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Streaming episode " + episodeNumber + " of " + showName;
    }

    @Override
    public String play(int fromSecond) {
        int minutes = fromSecond / 60;
        int seconds = fromSecond % 60;
        return "Streaming episode " + episodeNumber + " of " + showName + " from " + String.format("%d:%02d", minutes, seconds);
    }

    @Override
    public String pause() {
        return "Podcast paused: " + showName;
    }

    public String getShowName() {
        return showName;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }
}
