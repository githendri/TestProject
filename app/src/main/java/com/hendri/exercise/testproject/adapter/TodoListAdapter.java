package com.hendri.exercise.testproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hendri.exercise.testproject.R;
import com.hendri.exercise.testproject.database.DtbTodo;
import com.hendri.exercise.testproject.utils.UtilData;

import java.util.List;

/**
 * Created by hendri on 11/23/2016.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ListViewHolder> {
    private List<DtbTodo> listData;
    private Context context;

    public TodoListAdapter(Context context, List<DtbTodo> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_child, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        //check if there is data or not
        if (listData != null && listData.size() > 0) holder.setData(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private CheckBox cbCompleted;

        public ListViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            cbCompleted = (CheckBox) itemView.findViewById(R.id.cbCompleted);
        }

        public void setData(DtbTodo data) {
            tvTitle.setText(data.getTitle());
            cbCompleted.setChecked(data.getCompleted());
            //dont know if the test required the checklist be checkable
//            cbCompleted.setEnabled(true);
//            cbCompleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    data.setCompleted(isChecked);
//                    UtilData.insertOrReplaceDtbTodo(context.getApplicationContext(), data);
//                }
//            });
        }
    }
}
