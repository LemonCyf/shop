/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/14 8:27
 * @description:
 */
public class Face {
    private String image_id;
    private String request_id;
    private Integer time_used;
    private Faces[] faces;
}
class Faces{
    private F1 f1;
    private F2 f2;
    private F3 f3;
    private F4 f4;
}
class F1{
    private Location mouth;
    private Location countour;
    private Location right;
    private Location mouthUpper;
}
class Location{
    private Integer y;
    private Integer x;
}

class F2{
    private Gender gender;
    private Age age;
    private Glass glass;
    private Headpose headpose;
    private Smile smile;
}
class Gender{
    private String values;
}
class Age{
    private Integer values;
}
class Glass{
    private String values;
}
class Headpose{
    private Double yaw_angle;
    private Double pitch_angle;
    private Double roll_angle;
}
class Smile{
    private Double threshold;
    private Double value;
}

class F3{
    private Integer width;
    private Integer top;
    private Integer left;
    private Integer height;
}
class F4{
    private String face_token;
}






