package com.example.prady.BusPass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    Context mcontext;
    int resource;
    List<User> userList;
    DatabaseHelper mdatabase;

    public UserAdapter(Context mcontext, int resource, List<User> userList, DatabaseHelper mdatabase) {
        super(mcontext, resource, userList);
        this.mcontext=mcontext;
        this.resource=resource;
        this.userList=userList;
        this.mdatabase=mdatabase;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View view=inflater.inflate(resource, null);

        TextView textName= (TextView) view.findViewById(R.id.Name);
        TextView textDOB= (TextView) view.findViewById(R.id.DOB);
        TextView textPhn= (TextView) view.findViewById(R.id.Phone);
        TextView textAdhar= (TextView) view.findViewById(R.id.Adhar);
        TextView textCity=view.findViewById(R.id.City);
        TextView textUsername= (TextView) view.findViewById(R.id.Username);
        TextView textPass= (TextView) view.findViewById(R.id.Pass);
        TextView textValue= (TextView) view.findViewById(R.id.Value);

        final User user=userList.get(position);

        textName.setText(user.getName());
        textDOB.setText(user.getDob());
        textPhn.setText(user.getPhone());
        textAdhar.setText(user.getAdhar());
        textCity.setText(user.getCity());
        textUsername.setText(user.getUsername());
        textPass.setText(user.getPass());
        textValue.setText(user.getValue());
         return view;

    }
}
