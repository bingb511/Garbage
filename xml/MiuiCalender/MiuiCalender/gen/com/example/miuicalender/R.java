/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package com.example.miuicalender;

public final class R {
    public static final class attr {
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int calendarViewStyle=0x7f010001;
        /** <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static final int state_current=0x7f010000;
    }
    public static final class color {
        public static final int blue_dark=0x7f040004;
        public static final int blue_light=0x7f040003;
        public static final int stroke=0x7f040005;
        public static final int text_calendar=0x7f040006;
        public static final int text_light=0x7f040001;
        public static final int text_normal=0x7f040000;
        public static final int text_super_light=0x7f040002;
    }
    public static final class dimen {
        /**  Default screen margins, per the Android Design guidelines. 

         Customize dimensions originally defined in res/values/dimens.xml (such as
         screen margins) for sw720dp devices (e.g. 10" tablets) in landscape here.
    
         */
        public static final int activity_horizontal_margin=0x7f050000;
        public static final int activity_vertical_margin=0x7f050001;
    }
    public static final class drawable {
        public static final int bg_btn_calendar=0x7f020000;
        public static final int bg_btn_calendar_activated=0x7f020001;
        public static final int bg_btn_calendar_pressed=0x7f020002;
        public static final int bg_calendar_stroke=0x7f020003;
        public static final int ic_arrow_left=0x7f020004;
        public static final int ic_arrow_left_disabled=0x7f020005;
        public static final int ic_arrow_left_normal=0x7f020006;
        public static final int ic_arrow_left_pressed=0x7f020007;
        public static final int ic_arrow_right=0x7f020008;
        public static final int ic_arrow_right_disabled=0x7f020009;
        public static final int ic_arrow_right_normal=0x7f02000a;
        public static final int ic_arrow_right_pressed=0x7f02000b;
        public static final int ic_launcher=0x7f02000c;
    }
    public static final class id {
        public static final int action_settings=0x7f090008;
        public static final int calendar=0x7f090000;
        public static final int days=0x7f090006;
        public static final int header=0x7f090001;
        public static final int next=0x7f090004;
        public static final int prev=0x7f090002;
        public static final int selection_title=0x7f090005;
        public static final int title=0x7f090003;
        public static final int weeks=0x7f090007;
    }
    public static final class layout {
        public static final int activity_main=0x7f030000;
        public static final int calendar_layout=0x7f030001;
        public static final int day_layout=0x7f030002;
        public static final int week_layout=0x7f030003;
    }
    public static final class menu {
        public static final int main=0x7f080000;
    }
    public static final class string {
        public static final int app_name=0x7f060001;
        public static final int time_next=0x7f060000;
        public static final int time_prev=0x7f060002;
    }
    public static final class style {
        /** 
        Base application theme for API 11+. This theme completely replaces
        AppBaseTheme from res/values/styles.xml on API 11+ devices.
    
 API 11 theme customizations can go here. 

        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    
 API 14 theme customizations can go here. 
         */
        public static final int AppBaseTheme=0x7f070002;
        public static final int CalendarStyle=0x7f070001;
        public static final int CalendarTheme=0x7f070000;
    }
    public static final class styleable {
        /** Attributes that can be used with a day.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #day_state_current com.example.miuicalender:state_current}</code></td><td></td></tr>
           </table>
           @see #day_state_current
         */
        public static final int[] day = {
            0x7f010000
        };
        /**
          <p>This symbol is the offset where the {@link com.example.miuicalender.R.attr#state_current}
          attribute's value can be found in the {@link #day} array.


          <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name com.example.miuicalender:state_current
        */
        public static final int day_state_current = 0;
    };
}
