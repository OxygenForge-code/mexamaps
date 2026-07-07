package app.mexamaps.intent;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import app.mexamaps.MwmActivity;

public interface IntentProcessor
{
  @Nullable
  boolean process(@NonNull Intent intent, @NonNull MwmActivity activity);
}
