package edu.cmu.qatar.cs214.hw.hw5.plugin.wordprefix;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import edu.cmu.qatar.cs214.hw.hw5.Emitter;
import edu.cmu.qatar.cs214.hw.hw5.MapTask;

/**
 * The map task for a word-prefix map/reduce computation.
 */
public class WordPrefixMapTask implements MapTask {
    private static final long serialVersionUID = 3046495241158633404L;

    
    //Code copied from WorkCountMap Task in lecture 26
    @Override
    public void execute(InputStream in, Emitter emitter) throws IOException {
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\W+");
        while (scanner.hasNext()) {
            String key = scanner.next().trim().toLowerCase();
            emitter.emit(key, "1");
        }
        scanner.close();
    }

}
