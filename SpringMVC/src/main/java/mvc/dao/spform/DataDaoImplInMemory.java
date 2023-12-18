package mvc.dao.spform;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import mvc.bean.spform.EducationData;
import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;

@Repository
public class DataDaoImplInMemory implements DataDao {

	@Override
	public List<EducationData> findAllEducationDatas() {
		// 小學, 國中, 高中, 大學, 研究所
		List<EducationData> educationDatas = Arrays.asList(
				new EducationData(1, "小學"),
				new EducationData(2, "國中"),
				new EducationData(3, "高中"),
				new EducationData(4, "大學"),
				new EducationData(5, "研究所"));
		return educationDatas;
	}

	@Override
	public Optional<EducationData> getEducationDataById(Integer id) {
		List<EducationData> educationDatas = findAllEducationDatas();
		return educationDatas.stream().filter(edu -> edu.getId().equals(id)).findFirst();
	}

	@Override
	public List<SexData> findAllSexDatas() {
		return Arrays.asList(new SexData(1, "男"), new SexData(2, "女"));
	}

	@Override
	public Optional<SexData> getSexDataById(Integer id) {
		return findAllSexDatas().stream().filter(sex -> sex.getId().equals(id)).findFirst();
	}

	@Override
	public List<InterestData> finAllInterestDatas() {
		return Arrays.asList(
				new InterestData(1, "爬山"), new InterestData(2, "看電影"),
				new InterestData(3, "看書📘"), new InterestData(4, "聽音樂🎵"),
				new InterestData(5, "國畫"), new InterestData(6, "FPV"));
	}

	@Override
	public Optional<InterestData> getInterestDataById(Integer id) {
		return finAllInterestDatas().stream().filter(interest -> interest.getId().equals(id)).findFirst();
	}
	
}