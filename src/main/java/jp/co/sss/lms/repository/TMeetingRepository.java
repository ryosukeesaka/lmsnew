package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.lms.entity.TMeeting;
import jp.co.sss.lms.form.MeetingForm;

public interface TMeetingRepository extends JpaRepository<TMeeting, Integer>{

	String save(MeetingForm meetingForm);
}
