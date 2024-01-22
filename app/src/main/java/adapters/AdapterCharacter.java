package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ies.carrillo.android.rickandmortyapi.R;
import models.Character;

public class AdapterCharacter extends ArrayAdapter<Character> {
    private List<Character> characterList;

    public AdapterCharacter(Context context, List<Character> characterList) {
        super(context, 0, characterList);
        this.characterList = characterList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Character character = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_character, parent, false);
        }

        TextView nameCharacter = convertView.findViewById(R.id.tvNombrePersonaje);

        nameCharacter.setText(character.getName());

        return convertView;
    }
}
