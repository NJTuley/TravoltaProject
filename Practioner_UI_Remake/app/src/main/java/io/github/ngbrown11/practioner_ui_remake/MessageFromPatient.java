package io.github.ngbrown11.practioner_ui_remake;

import android.app.Activity;
import android.media.Image;

/**
 * Created by nicholas on 11/19/2016.
 */

public class MessageFromPatient extends Activity {
    private String sender_id;
    private String subject;
    private String content;
    private String severity;
    private Image image;

    public MessageFromPatient()
    {
        sender_id = "NULL";
        content = "NULL";
        image = null;
    }

    MessageFromPatient(String _sender, String _subject, String _content, String _severity, Image _img)
    {
        setId(_sender);
        setSubject(_subject);
        setContent(_content);
        setSeverity(_severity);
        setImage(_img);

    }

    public void setId(String _id)
    {
        if(_id != null && !isEmpty(_id))
            this.sender_id = _id;
    }

    public void setSubject(String _subj)
    {
        if(_subj != null && !isEmpty(_subj))
            this.subject = _subj;
    }

    public void setContent(String _content)
    {
        if(_content != null && !isEmpty(_content))
            this.content = _content;
    }

    public void setSeverity(String _severity)
    {
        if(_severity != null && !isEmpty(_severity))
            this.severity = _severity;
    }

    public void setImage(Image _img)
    {
        if(_img != null)
        {
            this.image = _img;
        }
    }

    public boolean isEmpty(String str)
    {
        if(str.equals(""))
            return true;
        else
            return false;
    }

    public String getSenderId()
    {
        return this.sender_id;
    }

    public String getSubject()
    {
        return this.subject;
    }

    public String getContent()
    {
        return this.content;
    }

    public String getSeverity()
    {
        return this.severity;
    }

    public Image getImage()
    {
        return this.image;
    }
}
