package wia.soft.official.pastebin.utils;

import org.simpleframework.xml.core.Persister;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import wia.soft.official.pastebin.data.models.PasteNetwork;
import wia.soft.official.pastebin.data.models.User;

public class Parse {

    public static List<PasteNetwork> parsePaste(String answer) {
        String regex = "</paste>";
        String[] sArr = answer.split(regex);

        for (int i = 0; i < sArr.length; i++) {
            sArr[i] += regex;
        }

        List<String> pasteList = new ArrayList<>();

        for (int i = 0; i < sArr.length; i++) {
            pasteList.add(sArr[i]);
        }

        pasteList.remove(pasteList.size() - 1);

        List<PasteNetwork> pasteNetworkTrendingList = new ArrayList<>();

        for (int i = 0; i < pasteList.size(); i++) {
            Reader reader = new StringReader(pasteList.get(i));
            Persister serializer = new Persister();
            try {
                pasteNetworkTrendingList.add(serializer.read(PasteNetwork.class, reader, false));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pasteNetworkTrendingList;
    }


    public static User parseAnswer(String answer) {
        User user;

        Reader reader = new StringReader(answer);
        Persister serializer = new Persister();

        try {
            user = serializer.read(User.class, reader, false);
        } catch (Exception e) {
            e.printStackTrace();
            user = parseWrongAnswer(answer);
        }
        return user;
    }

    private static User parseWrongAnswer(String wrongAnswer) {
        String answer = "";
        String regexWebsite = "</user_website>";
        String regexLocation = "</user_location>";

        String[] dividedAnswer = new String[3];
        dividedAnswer[0] = wrongAnswer.split(regexWebsite)[0];
        dividedAnswer[1] = wrongAnswer.split(regexWebsite)[1].split(regexLocation)[0];
        dividedAnswer[2] = wrongAnswer.split(regexLocation)[1];

        char[] charsDividedAnswer0 = dividedAnswer[0].toCharArray();
        String lastCharAnswer0 = String.valueOf(charsDividedAnswer0[dividedAnswer[0].length() - 1]);

        if (lastCharAnswer0.equals(">")) {
            answer += dividedAnswer[0] + "unknown" + regexWebsite + dividedAnswer[1];
        } else {
            answer += dividedAnswer[0] + dividedAnswer[1];
        }

        char[] charsDividedAnswer1 = dividedAnswer[1].toCharArray();
        String lastCharAnswer1 = String.valueOf(charsDividedAnswer1[dividedAnswer[1].length() - 1]);
        String s = "";
        if (lastCharAnswer1.equals(">")) {
            answer += "unknown" + regexLocation + dividedAnswer[2];
        } else {
            answer += dividedAnswer[2];
        }

        return parseAnswer(answer);
    }
}