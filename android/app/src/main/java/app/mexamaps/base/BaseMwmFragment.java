package app.mexamaps.base;

import android.content.Context;
import androidx.fragment.app.Fragment;
import app.mexamaps.util.Utils;

public class BaseMwmFragment extends Fragment implements OnBackPressListener
{
  @Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    Utils.detachFragmentIfCoreNotInitialized(context, this);
  }

  @Override
  public boolean onBackPressed()
  {
    return false;
  }
}
