package markkarabajakian.linkedin;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.KeyEvent;
import android.widget.RemoteViews;
import android.provider.MediaStore;
//import android.view;



/**
 * A {@link Service} that publishes a {@link LiveCard} in the timeline.
 */


public class linkedIn extends Service {

    private static final String LIVE_CARD_TAG = "linkedIn";

    private LiveCard mLiveCard;
    private KeyEvent x;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mLiveCard == null) {
            mLiveCard = new LiveCard(this, LIVE_CARD_TAG);

            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.linked_in);
            mLiveCard.setViews(remoteViews);

            // Display the options menu when the live card is tapped.
            Intent menuIntent = new Intent(this, LiveCardMenuActivity.class);
            mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
            mLiveCard.publish(PublishMode.REVEAL);

        } else {
            mLiveCard.navigate();
        }

        return START_STICKY;
    }

/*    public int onTap(Intent intent, KeyEvent tap) {
        if (tap != null){
               //take Picture
               //return picture file
        }
    }
*/

    @Override
    public void onDestroy() {
        if (mLiveCard != null && mLiveCard.isPublished()) {
            mLiveCard.unpublish();
            mLiveCard = null;
        }
        super.onDestroy();
    }
}
