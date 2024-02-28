package com.tamerlan.movies.view;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.tamerlan.movies.R;
import com.tamerlan.movies.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {
    public static final String TAG = "ReviewsAdapter";

    private static final String TYPE_POSITIVE = "Позитивный";
    private static final String TYPE_NEGATIVE = "Негативный";
    private static final String TYPE_NEUTRAL = "Нейтральный";
    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
        Log.d(TAG, "SetReviews works");
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.textViewAuthor.setText(review.getAuthor());
        holder.textViewDate.setText(review.getDate());
        holder.textViewTitle.setText(review.getTitle());
        holder.textViewReview.setText(review.getReview());
        String type = review.getType();
        int backgroundId =  R.drawable.review_red ;
        switch (type) {
            case TYPE_POSITIVE:
                backgroundId = R.drawable.review_green;
                break;
            case TYPE_NEGATIVE:
                backgroundId = R.drawable.review_red;
                break;
            case  TYPE_NEUTRAL:
                backgroundId = R.drawable.review_gray;
                break;
        }
//        if (type == TYPE_POSITIVE) {
//
//        } else if (type == TYPE_NEGATIVE) {
//            backgroundId = R.drawable.review_red;
//        } else {
//            backgroundId = R.drawable.review_gray;
//        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.imageViewType.setBackground(background);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewsViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewAuthor;
        private final TextView textViewDate;
        private final TextView textViewTitle;
        private final TextView textViewReview;
        private final ImageView imageViewType;

        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewReview = itemView.findViewById(R.id.textViewReview);
            imageViewType = itemView.findViewById(R.id.imageViewType);

        }
    }

}
