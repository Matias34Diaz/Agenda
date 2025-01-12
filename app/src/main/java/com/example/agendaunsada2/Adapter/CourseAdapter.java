// CourseAdapter.java
package com.example.agendaunsada2.Adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendaunsada2.Model.Course;
import com.example.agendaunsada2.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final List<Course> courses;

    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.courseNameTextView.setText(course.getName());
        holder.courseCodeTextView.setText(course.getCourseCode());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseNameTextView;
        private final TextView courseCodeTextView;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.courseNameTextView);
            courseCodeTextView = itemView.findViewById(R.id.courseCodeTextView);
        }
    }
}


