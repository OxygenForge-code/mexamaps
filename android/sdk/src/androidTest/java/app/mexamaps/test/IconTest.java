package app.mexamaps.test;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.test.platform.app.InstrumentationRegistry;
import app.mexamaps.sdk.bookmarks.data.Icon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IconTest
{
  @NonNull
  private final Context mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

  @Before
  public void init()
  {
    System.loadLibrary("mexamaps");
  }

  @Test
  public void testLoadingIconsFromCore()
  {
    try
    {
      Icon.loadDefaultIcons(mContext.getResources(), mContext.getPackageName());
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }
}
