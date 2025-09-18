import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { NgxFileDropModule, NgxFileDropEntry } from 'ngx-file-drop';
import { FormsModule } from '@angular/forms';
// ⬆️ only import NgxFileDropModule for Angular's "imports" metadata
// FileSystemFileEntry / FileSystemDirectoryEntry are DOM types — import them only for typing

import type { FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';

import { VideoService } from '../video.service';

@Component({
  selector: 'app-upload-video',
  standalone: true,
  imports: [CommonModule, NgxFileDropModule, MatButtonModule, FormsModule], // ✅ only modules/components/pipes here
  templateUrl: './upload-video.component.html',
  styleUrls: ['./upload-video.component.css']
})
export class UploadVideoComponent {
  public files: NgxFileDropEntry[] = [];
  fileUploaded: boolean = false;
  fileEntry: FileSystemFileEntry | undefined;

  constructor(private videoService: VideoService) { }

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;
    for (const droppedFile of files) {
      if (droppedFile.fileEntry.isFile) {
        this.fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        this.fileEntry.file((file: File) => {
          console.log(droppedFile.relativePath, file);

          this.fileUploaded = true;
          // Here you can access the real file
          // For example, you could use FormData to upload it to a server
//           const formData = new FormData();
//           formData.append('file', file, droppedFile.relativePath);

          // Example: Upload the file using fetch API
          /*
          fetch('your-upload-endpoint', {
            method: 'POST',
            body: formData
          }).then(response => {
            console.log('Upload successful:', response);
          }).catch(error => {
            console.error('Upload error:', error);
          });
          */
        });
      } else {
        const dirEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(droppedFile.relativePath, dirEntry);
      }
    }
  }

  upload() {
    if (this.fileEntry !== undefined) {
      this.fileEntry.file((file: File) => {
        this.videoService.uploadVideo(file).subscribe((data: any) => {
          console.log('Upload successful: ', data);
        });
      });
    }
  }

  public fileOver(event: DragEvent) {
    console.log(event);
  }

  public fileLeave(event: DragEvent) {
    console.log(event);
  }
}
