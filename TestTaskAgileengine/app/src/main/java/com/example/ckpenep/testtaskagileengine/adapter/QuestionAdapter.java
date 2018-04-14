package com.example.ckpenep.testtaskagileengine.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ckpenep.testtaskagileengine.R;
import com.example.ckpenep.testtaskagileengine.model.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ckpenep on 28.03.2018.
 */

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int QUESTIONS_VIEW_TYPE = 0;
    private static final int PROGRESS_VIEW_TYPE = 1;

    private OnScrollToBottomListener mOnScrollToBottomListener;
    private List<Question> questions ;

    public QuestionAdapter(OnScrollToBottomListener listener) {
        mOnScrollToBottomListener = listener;
        questions = new ArrayList<>();
    }

    public void setRepositories(List<Question> questions) {
        this.questions = new ArrayList<>(questions);
        dataSetChanged();
    }

    public void addRepositories(List<Question> questions) {
        this.questions.addAll(questions);
        dataSetChanged();
    }

    public void addLoading()
    {
        this.questions.add(null);
        notifyItemInserted(this.questions.size() - 1);
    }

    public void removeLoading()
    {
        if(this.questions.size() > 0) {
            this.questions.remove(this.questions.size() - 1);
            notifyItemRemoved(this.questions.size());
        }
    }

    boolean isLoaded;
    public void setLoaded()
    {
        isLoaded = false;
    }

    private void dataSetChanged() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return this.questions.get(position) == null ? PROGRESS_VIEW_TYPE : QUESTIONS_VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.size();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {



        if (holder instanceof ItemViewHolder) {
            Question question = questions.get(position);
            ((ItemViewHolder) holder).bind(question);
        }else if (holder instanceof FooterViewHolder) {
            ((FooterViewHolder) holder).mProgressBar.setIndeterminate(true);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == QUESTIONS_VIEW_TYPE) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.question_item, viewGroup, false);
            return new ItemViewHolder(v);

        } else if (viewType == PROGRESS_VIEW_TYPE) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loading_item, viewGroup, false);
            return new FooterViewHolder(view);

        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBar;
        public View view;

        public FooterViewHolder(View v) {
            super(v);
            view = v;

            mProgressBar = (ProgressBar)v.findViewById(R.id.progress_bar);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.count_answers)
        TextView count_answers;
        @BindView(R.id.date_created)
        TextView created_date;
        @BindView(R.id.tags)
        TextView tags;
        public View view;

        public ItemViewHolder(View v) {
            super(v);
            view = v;

            ButterKnife.bind(this, itemView);
        }

        void bind(Question item)
        {
            title.setText(item.getTitle());
            count_answers.setText(item.getAnswerCount().toString());
            created_date.setText(item.getDateByString());
            tags.setText(item.getTagsByString());

            setBorderColor(item.getAnswerCount());
        }

        private void setBorderColor(int count)
        {
            GradientDrawable bgShape = (GradientDrawable)count_answers.getBackground();
            if(count <= 0)
            {
                bgShape.setStroke(4, Color.BLACK);
                bgShape.setAlpha(25);
                count_answers.setTextColor(ContextCompat.getColor(count_answers.getContext(), R.color.answer_color_foreground));
            }
            else {

                bgShape.setStroke(4, ContextCompat.getColor(count_answers.getContext(), R.color.answer_background));
                bgShape.setAlpha(255);
                count_answers.setTextColor(ContextCompat.getColor(count_answers.getContext(), R.color.answer_background));
            }

        }
    }

    public interface  OnScrollToBottomListener
    {
        void onScrollToBottom();
    }
}