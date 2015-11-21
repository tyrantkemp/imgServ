package xz.com.bean;
/*@property(nonatomic,copy)NSString* iconurl;
@property(nonatomic,copy)NSString* idname;
@property(nonatomic,copy)NSString* toid;
@property(nonatomic,copy)NSString* des;
@property(nonatomic,strong)NSDate* time;
@property(nonatomic,assign)NSInteger isLZ;*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OtherComments {

	private String iconurl;
	private String idname;
	private String toid;
	private String des;
	private String time;
	private int isLZ ;
	public OtherComments() {
		this.iconurl="";
		this.idname="";
		this.toid="";
		this.des="";
		this.time="2015.1.1";
		this.isLZ=0;
	}
	public String getIconurl() {
		return iconurl;
	}
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
	public String getIdname() {
		return idname;
	}
	public void setIdname(String idname) {
		this.idname = idname;
	}
	public String getToid() {
		return toid;
	}
	public void setToid(String toid) {
		this.toid = toid;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getIsLZ() {
		return isLZ;
	}
	public void setIsLZ(int isLZ) {
		this.isLZ = isLZ;
	}


}
