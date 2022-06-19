package com.ownproject.Sorganizer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RepRequestRepository extends JpaRepository<RepRequest, Integer> {

    List<RepRequest> findAllByUser(User user);

    @Query(value = "SELECT * FROM serviceorganizerjpa.reprequest where is_scheduled is false;", nativeQuery = true)
    List<RepRequest> findAllByIsScheduledFalse();

    void deleteById(Integer id);







  /*  @Select("SELECT *  from REPREQUEST where userid=#{userId}")
    List<RepRequest> getAllRequestsByUserId(Integer userId);

    @Select("SELECT *  from REPREQUEST where isscheduled='0'")
    List<RepRequest> getAllUnScheduledRequests();

    @Select("SELECT * FROM REPREQUEST WHERE clientname=#{clientName} and userid=#{userId}")
    RepRequest getRepairRequest(String clientName, Integer userId);

    @Select("SELECT * FROM serviceorganizer.reprequest where repreqid=#{repReqId}")
    RepRequest getRepReqById(Integer repReqId);

    @Insert("INSERT INTO REPREQUEST (timestamp,clientname,licenceplate,vinnumber, defectdescription,userid,ispartsordered,isscheduled,isfinished) " +
            "VALUES (#{timeStamp},#{clientName},#{licencePlate},#{vinNumber},#{defectDescription},#{userId},#{isPartsOrdered},#{isScheduled},#{isFinished})")
    @Options(useGeneratedKeys = true, keyProperty="repReqId")
    int insert(RepRequest repRequest);

    @Delete("DELETE from REPREQUEST where clientname=#{clientName} and userid=#{userid}")
    int delete(String clientName, Integer userid);

    @Update("UPDATE REPREQUEST SET clientname=#{clientName}, defectdescription=#{defectDescription},  WHERE repreqid = #{repReqId}")
    void updateRepairRequest(String clientName, String defectDescription, Integer repReqId );

    @Update("UPDATE REPREQUEST SET ispartsordered=#{isPartsOrdered} WHERE repreqid=#{repReqId}")
    void setOrderedStatus(Integer repReqId, Boolean isPartsOrdered);

    @Update("UPDATE REPREQUEST SET  isscheduled=#{isScheduled} WHERE repreqid=#{repReqId}")
    void setScheduledStatus(Integer repReqId,  Boolean isScheduled);

    @Update("UPDATE REPREQUEST SET isfinished=#{isFinished} WHERE repreqid=#{repReqId}")
    void setFinishedStatus(Integer repReqId,  Boolean isFinished);

*/
}
