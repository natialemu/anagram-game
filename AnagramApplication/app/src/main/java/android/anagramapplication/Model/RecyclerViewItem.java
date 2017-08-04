package android.anagramapplication.Model;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Nathnael on 8/4/2017.
 */

public class RecyclerViewItem {
    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    private String solution;

    public RecyclerViewItem(String solution){
        this.solution = solution;
    }
}
