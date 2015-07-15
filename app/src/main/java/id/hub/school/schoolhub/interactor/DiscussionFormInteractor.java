package id.hub.school.schoolhub.interactor;

import id.hub.school.schoolhub.presenter.DiscussionFormCreateListener;

public interface DiscussionFormInteractor {
    void validateCreateDiscussionForm(String category, String title, String question,
                                      DiscussionFormCreateListener listener);
}
