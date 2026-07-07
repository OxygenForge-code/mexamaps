package app.mexamaps.editor;

import androidx.fragment.app.Fragment;
import app.mexamaps.base.BaseMwmFragmentActivity;

public class ReportActivity extends BaseMwmFragmentActivity
{
  @Override
  protected Class<? extends Fragment> getFragmentClass()
  {
    return ReportFragment.class;
  }
}
