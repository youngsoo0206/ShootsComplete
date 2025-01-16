create table business_info(
      b_info_idx INT AUTO_INCREMENT PRIMARY KEY,
      business_idx INT,
      parking VARCHAR(120),
      shower BOOL,
      lounge BOOL,
      equipment TEXT,
      opening_hours VARCHAR(120),
      cctv BOOL,
      kiosk BOOL,
      rental  VARCHAR(255)
);



select * from business_info;