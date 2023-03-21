package cat.udl.gtidic.course2223.teacher.memory.helpers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Metodes auxiliars i compartits per diferents Activities
 */

public final class ActivityHelper {

    /**
     * Amaga el teclat virtual de la pantalla
     */
    public static void hideKeyboard(Activity activity){
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
