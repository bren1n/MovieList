package br.ufrn.imd.movielist.main.movieHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import br.ufrn.imd.movielist.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private RecyclerViewInterface recyclerViewInterface;

    public MovieAdapter(Context context, List<Movie> movieList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.movieList = movieList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    //Cria as visualizações (Converte o XML do nosso modelo para uma View)
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Converte o xml para uma view utilizando um inflater
        //O parent.getContext() reecupera o contexto no qual a lista sera inserida
        //O false é para não adicionar o elemento ao elemento root
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list, parent, false);
        return new MyViewHolder(listItem);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.name.setText(movie.getName());
        holder.releaseDate.setText(movie.getReleaseDate());

        Glide.with(context).load(movie.getImageUrl()).into(holder.imageUrl);
    }

    @Override
    public int getItemCount() {

        return movieList.size();
    }

    //Armazena cada um dos itens exibidos nos elementos da listagem
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView releaseDate;
        ImageView imageUrl;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.listName);
            releaseDate = itemView.findViewById(R.id.listReleaseDate);
            imageUrl = itemView.findViewById(R.id.listImageUrl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
