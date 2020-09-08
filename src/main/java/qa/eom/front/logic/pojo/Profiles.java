package qa.eom.front.logic.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Profiles {


    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("roles")
    private List<String > roles;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("agree_pers_data")
    private boolean agreePersData;

    @JsonProperty("agreement_accepted")
    private boolean agreementAccepted;

    @JsonProperty("school_id")
    private String schoolId;

    @JsonProperty("school_shortname")
    private String schoolShortname;

    @JsonProperty("subject_ids")
    private List<String> subjectIds;


    public String getId() { return id; }
    public String getType() { return type; }
    public List<String> getRoles() { return roles; }
    public String getUserId() { return userId; }
    public boolean isAgreePersData() { return agreePersData; }
    public boolean isAgreementAccepted() { return agreementAccepted; }
    public String getSchoolId() { return schoolId; }
    public String getSchoolShortname() { return schoolShortname; }
    public List<String> getSubjectIds() { return subjectIds; }


    @Override
    public String toString() {

        return
                "Profiles {" +
                        "id = '" + id + '\'' +
                        ",type = '" + type + '\'' +
                        ",roles = '" + roles + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",agree_pers_data = '" + agreePersData + '\'' +
                        ",agreement_accepted = '" + agreementAccepted + '\'' +
                        ",school_id = '" + schoolId + '\'' +
                        ",school_shortname = '" + schoolShortname + '\'' +
                        ",subject_ids = '" + subjectIds + '\'' +
                        "}"
                ;
    }
}
