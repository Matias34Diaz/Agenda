package com.example.agendaunsada2.Model;

import java.util.ArrayList;
import java.util.List;

public class StatusUpdateRequest {
    private List<Update> updates;

    public StatusUpdateRequest() {
        this.updates = new ArrayList<>();
    }

    public void addUpdate(Update update) {
        updates.add(update);
    }

    public List<Update> getUpdates() {
        return updates;
    }

    public static class Update {
        private int courseId;
        private int statusId;

        public Update(int courseId, int statusId) {
            this.courseId = courseId;
            this.statusId = statusId;
        }

        public int getCourseId() {
            return courseId;
        }

        public int getStatusId() {
            return statusId;
        }
    }
}
