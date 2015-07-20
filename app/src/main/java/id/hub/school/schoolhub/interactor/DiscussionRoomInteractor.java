package id.hub.school.schoolhub.interactor;

import id.hub.school.schoolhub.presenter.LoadDiscussionRoomListener;

public interface DiscussionRoomInteractor {
    void loadDiscussionRoom(LoadDiscussionRoomListener listener);

    void reloadDiscussionRoom(LoadDiscussionRoomListener listener);

    void loadMoreDiscussionRoom(int current_page, LoadDiscussionRoomListener listener);
}
