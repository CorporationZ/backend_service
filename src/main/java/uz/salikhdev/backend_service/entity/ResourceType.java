package uz.salikhdev.backend_service.entity;

public enum ResourceType {
    NONE,
    PNG,
    JPEG,
    JPG,
    MP4,
    PDF;

    public static ResourceType getResourceType(String extension) {
        if (extension.contains("png")) {
            return ResourceType.PNG;
        } else if (extension.contains("jpeg")) {
            return ResourceType.JPEG;
        } else if (extension.contains("PDF")) {
            return ResourceType.PDF;
        } else if (extension.contains("mp4")) {
            return ResourceType.MP4;
        } else if (extension.contains("jpg")) {
            return ResourceType.JPG;
        } else {
            return ResourceType.NONE;
        }
    }
}
