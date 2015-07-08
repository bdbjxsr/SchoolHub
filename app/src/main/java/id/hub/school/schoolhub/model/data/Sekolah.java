package id.hub.school.schoolhub.model.data;

import java.util.List;

public class Sekolah {
    public final List<Results> results;

    public Sekolah(List<Results> results) { this.results = results; }

    public static final class Results {
        public final String Alamat;
        public final String Sekolah;
        public final String Tipe;
        public final String Wilayah;
        public final String createdAt;
        public final String objectId;
        public final String updateAt;

        public Results(String alamat, String sekolah, String tipe, String wilayah,
                       String createdAt, String objectId, String updateAt) {
            this.Alamat = alamat;
            this.Sekolah = sekolah;
            this.Tipe = tipe;
            this.Wilayah = wilayah;
            this.createdAt = createdAt;
            this.objectId = objectId;
            this.updateAt = updateAt;
        }
    }
}
