package edu.cmu.qatar.cs214.hw.hw5.plugin.wordprefix;

import java.io.IOException;
import java.util.Iterator;

import edu.cmu.qatar.cs214.hw.hw5.Emitter;
import edu.cmu.qatar.cs214.hw.hw5.ReduceTask;

/**
 * The reduce task for a word-prefix map/reduce computation.
 */
public class WordPrefixReduceTask implements ReduceTask {
    private static final long serialVersionUID = 6763871961687287020L;

    @Override
    public void execute(String key, Iterator<String> values, Emitter emitter) throws IOException {
        // TODO: Implement this!
    }

}
