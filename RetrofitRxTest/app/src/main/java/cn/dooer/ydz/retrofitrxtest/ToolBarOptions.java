package cn.dooer.ydz.retrofitrxtest;


/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class ToolBarOptions {
    /**
     * toolbar的title资源id
     */
    public int titleId = 0;
    /**
     * toolbar的title
     */
    public String titleString;
    /**
     * toolbar的返回按钮资源id，默认开启的资源nim_actionbar_dark_back_icon
     */
    //public int navigateId = R.drawable.icon_back;
    public int navigateId = R.drawable.ic_arrow_back_black_24dp;
    /**
     * toolbar的返回按钮，默认开启
     */
    public boolean isNeedNavigate = true;
}
