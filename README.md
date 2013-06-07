Distributed Speaker Diarization System
======================================

PJIIT Engineer's Thesis
---------------------------------------


Speaker diarization is a problem in computer science of segmenting the recorded speech into fragments corresponding to speakers that were present in that recording.

Using the technologies of web applications, the high performance clustering algorithm and distributing the system, made it possible for unlimited number of users to access the functionalities provided and getting high performance results in multiple parallel requests handling.

Project is divided into maven modules that correspond to distributed web applications responsible for different functionalities in the system.

System workflow:

- Receiving audio recording from the user in form of an audio file
- Audio file conversion into one that is supported by the system
- Speech features extraction from the speech signal to the spectral data with use of MFCC
- Automatic speaker clustering using the kNN algorithm
- Splitting the audio file into the segments
- Sharing the result audio files for users to access and sending an email notification

Project has been developed using following technologies:
  Maven, Java EE, Spring MVC, Memcached, FFMPEG, Comirva, Weka, jQuery, HTML 5 Audio
