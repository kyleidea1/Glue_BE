package org.glue.glue_be.meeting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;
import org.glue.glue_be.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "meeting")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_id", nullable = false, updatable = false)
    private Long meetingId;

    @Column(name = "meeting_title", nullable = false)
    private String meetingTitle;

    @OneToMany(mappedBy = "meeting")
    private List<Participant> participants = new ArrayList<>();

    @Column(name = "meeting_time", nullable = false)
    private LocalDateTime meetingTime;

    @Column(name = "min_ppl", nullable = false)
    private Integer minPpl;

    @Column(name = "max_ppl", nullable = false)
    private Integer maxPpl;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "meeting_place_latitude", nullable = true)
    private Double meetingPlaceLatitude;

    @Column(name = "meeting_place_longitude", nullable = true)
    private Double meetingPlaceLongitude;

    @Column(name = "meeting_place_name", nullable = true)
    private String meetingPlaceName;

    @Builder
    private Meeting(String meetingTitle,
                    LocalDateTime meetingTime,
                    Integer minPpl,
                    Integer maxPpl,
                    Integer status,
                    Double meetingPlaceLatitude,
                    Double meetingPlaceLongitude,
                    String meetingPlaceName) {
        this.meetingTitle = meetingTitle;
        this.meetingTime = meetingTime;
        this.minPpl = minPpl;
        this.maxPpl = maxPpl;
        this.status = status;
        this.meetingPlaceLatitude = meetingPlaceLatitude;
        this.meetingPlaceLongitude = meetingPlaceLongitude;
        this.meetingPlaceName = meetingPlaceName;
        this.participants = new ArrayList<>();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public void changeTitle(String newTitle) {
        this.meetingTitle = newTitle;
    }

    public void changeLocation(Double latitude, Double longitude, String placeName) {
        this.meetingPlaceLatitude = latitude;
        this.meetingPlaceLongitude = longitude;
        this.meetingPlaceName = placeName;
    }


    public void changeMinimumCapacity(int newMinPpl) {
        this.minPpl = newMinPpl;
    }

    public void changeMaximumCapacity(int newMaxPpl) {
        this.maxPpl = newMaxPpl;
    }

    public void rescheduleMeeting(LocalDateTime newTime) {
        this.meetingTime = newTime;
    }

    public void changeStatus(int newStatus) {
        this.status = newStatus;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
        participant.updateMeeting(this);
    }

    public boolean isMeetingFull() {
        return this.participants.size() >= this.maxPpl;
    }
}
