task :default => [:build]

task :build do
  system( "ejekyll" )
end

task :deploy => [:build] do
  dest = "/var/www"
  cp_r Dir.glob( "_site/**" ), "#{dest}"
  puts "Deployed _site to #{dest}"
end
