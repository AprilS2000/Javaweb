package mvc.bean.spform;


// 教育程度資料
public class EducationData extends BaseData{

	
	public EducationData() {
		
	}
	
	public EducationData(Integer id, String name) {
		super(id, name);
	}
	
	public String getDisplay() {
		return name + "(" + id + ")";
	}
}
