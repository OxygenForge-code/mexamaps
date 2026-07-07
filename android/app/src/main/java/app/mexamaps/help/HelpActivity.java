package app.mexamaps.help;

import androidx.fragment.app.Fragment;
import app.mexamaps.base.BaseToolbarActivity;

public class HelpActivity extends BaseToolbarActivity
{
  @Override
  protected Class<? extends Fragment> getFragmentClass()
  {
    return HelpFragment.class;
  }
}
