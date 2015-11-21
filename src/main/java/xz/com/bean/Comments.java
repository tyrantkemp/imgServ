package xz.com.bean;

import java.util.ArrayList;
import java.util.List;

/*@property(nonatomic,copy)NSString* url;
@property(nonatomic,copy)NSString* auther;
@property(nonatomic,copy)NSString* des;
@property(nonatomic,assign)NSInteger index;
@property(nonatomic,strong)NSMutableArray<MOOtherComments>* othercomments;*/
public class Comments {

	private String url ;
	private String  auther;
	private String  des;
	private int index;
	private List<OtherComments> othercomments  ;
	public Comments(){
		this.url="";
		this.auther="";
		this.des="";
		this.index=0;
		this.othercomments=new ArrayList<OtherComments>();
		this.othercomments.add(new OtherComments());
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<OtherComments> getOthercomments() {
		return othercomments;
	}
	public void setOthercomments(List<OtherComments> othercomments) {
		this.othercomments = othercomments;
	}
	
}
