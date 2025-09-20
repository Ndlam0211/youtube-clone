import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VideoUploadResponse } from './upload-video/VideoUploadResponse';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(file: File): Observable<VideoUploadResponse> {
    const formData = new FormData();
    formData.append('file', file, file.name);

    return this.httpClient.post<VideoUploadResponse>('http://localhost:8080/api/v1/videos', formData);
  }
}
