package xz.com.bean;

import java.util.ArrayList;
import java.util.List;

/*@property(nonatomic,copy)NSString* ID;
@property(nonatomic,copy)NSString* url;
@property(nonatomic,copy)NSString* des;
@property(nonatomic,copy)NSString* auther;
@property(nonatomic,assign)NSInteger top;
@property(nonatomic,assign)NSInteger down ;
@property(nonatomic,strong)NSMutableArray<NSComment>* comments ;*/
public class Documents {
private String ImgId;
private String nameId;
private String  url ;
private String   des;
private String   auther;
private int   top;
private int down;
private List<Comments> comments;
private String imageUrl;

public Documents(String imgId,String imgUrl){
	this.imageUrl=imgUrl;
	this.nameId="";
	this.url="";
	this.des="";
	this.auther="";
	this.top=0;
	this.down=0;
	this.ImgId=imgId;
	this.comments=new ArrayList<Comments>();
	
	this.comments.add(new Comments());
	
}
public Documents(){
	this.imageUrl="";
	this.nameId="";
	this.url="";
	this.des="";
	this.auther="";
	this.top=0;
	this.down=0;
	this.ImgId="";
	this.comments=new ArrayList<Comments>();
	this.comments.add(new Comments());

}

public String getImgId() {
	return ImgId;
}
public void setImgId(String imgId) {
	ImgId = imgId;
}
public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

public String getNameId() {
	return nameId;
}
public void setNameId(String nameId) {
	this.nameId = nameId;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getDes() {
	return des;
}
public void setDes(String des) {
	this.des = des;
}
public String getAuther() {
	return auther;
}
public void setAuther(String auther) {
	this.auther = auther;
}
public int getTop() {
	return top;
}
public void setTop(int top) {
	this.top = top;
}
public int getDown() {
	return down;
}
public void setDown(int down) {
	this.down = down;
}
public List<Comments> getComments() {
	return comments;
}
public void setComments(List<Comments> comments) {
	this.comments = comments;
}
	
}
