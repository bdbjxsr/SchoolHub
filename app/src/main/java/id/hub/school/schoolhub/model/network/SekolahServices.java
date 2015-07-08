package id.hub.school.schoolhub.model.network;

import id.hub.school.schoolhub.presenter.ParseQueryCompleteListener;

public interface SekolahServices {
    void getListSekolah(ParseQueryCompleteListener listener);

    void getListSekolah(String name, ParseQueryCompleteListener listener);
}
