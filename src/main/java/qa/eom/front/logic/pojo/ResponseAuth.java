package qa.eom.front.logic.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAuth {

    @JsonProperty("id")
    private String id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("profiles")
    private List<Profiles> profiles;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("authentication_token")
    private String authenticationToken;

    @JsonProperty("password_change_required")
    private boolean passwordChangeRequired;

    @JsonProperty("regional_auth")
    private String regionalAuth;


    public String getId() { return id; }
    public String getEmail() { return email; }
    public List<Profiles> getProfiles() { return profiles; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMiddleName() { return middleName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAuthenticationToken() { return authenticationToken; }
    public boolean isPasswordChangeRequired() { return passwordChangeRequired; }
    public String getRegionalAuth() { return regionalAuth; }


    @Override
    public String toString() {
        return
                "ResponseAuth {" +
                        "id = '" + id + '\'' +
                        ",email = '" + email + '\'' +
                        ",profiles = '" + profiles + '\'' +
                        ",first_name = '" + firstName + '\'' +
                        ",last_name = '" + lastName + '\'' +
                        ",middle_name = '" + middleName + '\'' +
                        ",phone_number = '" + phoneNumber + '\'' +
                        ",authentication_token = '" + authenticationToken + '\'' +
                        ",password_change_required = '" + passwordChangeRequired + '\'' +
                        ",regional_auth = '" + regionalAuth +
                        "}"
                ;
    }

}
