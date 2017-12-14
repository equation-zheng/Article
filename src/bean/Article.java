package bean;

import java.util.Date;

import annotation.Column;
import annotation.Table;

@Table(tableName = "t_article")
public class Article {
	@Column(field = "id", type = "varchar(100)", primaryKey = true)
	private String id;  //����
	
	@Column(field = "header", type = "varchar(100)")
	private String header;  //����
	
	@Column(field = "name", type = "varchar(60)")
	private String name;  //��������
	
	@Column(field = "content", type = "text")
	private String content;  //��������
	
	@Column(field = "author", type = "varchar(30)")
	private String author;  //����
	
	@Column(field = "description", type = "varchar(100)")
	private String description; //��Ҫ
	
	@Column(field = "is_published", type = "int(1)")
	private Integer isPublished;  //�Ƿ񷢲�  0 δ����  1 ����
	
	@Column(field = "is_delete", type = "int(1)")
	private Integer isDelete;	//�Ƿ�ɾ��  0 δɾ�� 1 ��ɾ��
	
	@Column(field = "create_time", type = "datetime")
	private Date create_time;	//����ʱ��
	
	@Column(field = "update_time", type = "timestamp", defaultNull = false)
	private Date updateTime;	//������ʱ��
	
	@Column(field = "user_id", type = "varchar(100)", defaultNull = false)
	private String userId;	//����ID
	
	@Column(field = "category_id", type = "int(2)", defaultNull = false)
	private Integer category_id;	//����ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", header=" + header + ", name=" + name
				+ ", content=" + content + ", author=" + author
				+ ", description=" + description + ", isPublished="
				+ isPublished + ", isDelete=" + isDelete + ", create_time="
				+ create_time + ", updateTime=" + updateTime + ", userId="
				+ userId + ", category_id=" + category_id + "]";
	}
}























