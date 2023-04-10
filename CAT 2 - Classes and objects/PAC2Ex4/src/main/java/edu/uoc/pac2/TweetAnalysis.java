package edu.uoc.pac2;

public class TweetAnalysis {

    public int countHashtags(String tweetText) {
        int count;

        count = 0;
        for (int i = 0; i < tweetText.length(); i++)
            if (tweetText.charAt(i) == '#')
                count++;
        return(count);
    }

    public String highlightUsernames(String tweetText, String[] usernames) {
        String          []strings;
        StringBuilder   out;
        int             index;

        out = new StringBuilder(tweetText);
        for (String user : usernames) {
            for (int i = 0; i < out.length(); i++) {
                index = out.substring(i).indexOf(user);
                if (index != -1) {
                    out.insert(i + index, "@");
                    i += index + user.length();
                }
            }
        }
        return (out.toString());
    }

    public String getTweetHistory(String[] tweetTexts) {
        StringBuffer out = new StringBuffer();
        String []info;

        for (int i = 0; i < tweetTexts.length; i++) {
            info = tweetTexts[i].split(":");
            out.append("User @").append(info[0]).append(" tweeted: '").append(info[1]);
            for (int j = 2; j < info.length; j++)
                out.append(':').append(info[j]);
            out.append("'");
            if (i + 1 < tweetTexts.length)
                out.append(System.lineSeparator());
        }
        return (out.toString());
    }

    public String botTweet(String[] words, int maxLength) {
        StringBuffer out = new StringBuffer();

        if (words.length == 0)
            return (out.toString());
        while (true) {
            for (String word : words) {
                if (out.length() + word.length() + 1 > maxLength)
                    return (out.toString());
                if (out.length() != 0)
                    out.append(' ');
                out.append(word);
            }
        }
    }
}
