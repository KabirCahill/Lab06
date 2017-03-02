package a00878366.comp3717.bcit.ca.lab06;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColourChangerTask colourChangerTask = new ColourChangerTask();
        colourChangerTask.execute(Color.GREEN, Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK, Color.WHITE, Color.CYAN, Color.DKGRAY, Color.TRANSPARENT, Color.YELLOW);
    }

    private class ColourChangerTask
            extends AsyncTask<Integer, Void, Integer>
    {
        private final String TAG = MainActivity.class.getName();

        protected void changeColor(final int color) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getWindow().getDecorView().setBackgroundColor(color);
                }
            });
        }

        @Override
        protected Integer doInBackground(final Integer... params)
        {
            try
            {
                int i = 0;

                while(true) {
                    Thread.sleep(200);

                    changeColor(params[i++]);

                    if(i >= params.length) {
                        i = 0;
                    }
                }
            }
            catch(final InterruptedException ex)
            {
                Log.e(TAG, "Interrupted", ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(final Integer colour)
        {
            getWindow().getDecorView().setBackgroundColor(colour);
        }
    }
}
