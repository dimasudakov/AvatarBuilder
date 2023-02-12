package app.Entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Avatar {
    private long id;
    private String name;
    private int hair_id;
    private int eye_id;
    private int mouth_id;
    private int client_id;

    public Avatar(String name, int hair_id, int eye_id, int mouth_id, int client_id) {
        this.name = name;
        this.hair_id = hair_id;
        this.eye_id = eye_id;
        this.mouth_id = mouth_id;
        this.client_id = client_id;
    }
}
